export class HttpError extends Error {
  constructor(
    public status: number,
    message: string,
    public response?: Response
  ) {
    super(message);
    this.name = "HttpError";
  }
}

/**
 * Generic helper function for making authenticated API requests
 * @param url - The URL to make the request to
 * @param options - The options for the request
 * @param authToken - Auth token provided in request for JWT authentication
 * @returns The response from the API
 */
export async function apiRequest<T>(
  url: string,
  options: RequestInit = {},
  authToken?: string
): Promise<T> {
  let response: Response;

  try {
    // Set default headers
    const headers: Record<string, string> = {
      "Content-Type": "application/json",
    };

    // Merge custom headers with default headers
    if (options.headers) {
      Object.assign(headers, options.headers);
    }

    // Add auth token if provided
    if (authToken) {
      headers.Authorization = `Bearer ${authToken}`;
    }

    // Create request options with merged headers
    const requestOptions: RequestInit = {
      ...options,
      headers,
    };

    // Only add body if it exists and method is not GET
    if (options.body && options.method !== "GET") {
      requestOptions.body = options.body;
    }

    response = await fetch(url, requestOptions);
  } catch (error) {
    // Only catch network errors here
    if (error instanceof TypeError) {
      throw new Error("Network error: Failed to connect to the server");
    }
    throw error;
  }

  const status: number = response.status;
  if (status >= 200 && status < 300) {
    // Check if response has content before trying to parse as JSON
    const contentType = response.headers.get("content-type");
    const hasJsonContent =
      contentType && contentType.includes("application/json");

    // For DELETE operations or empty responses, don't try to parse JSON
    if (options.method === "DELETE" || !hasJsonContent) {
      const text = await response.text();
      return (text ? JSON.parse(text) : undefined) as T;
    }

    return (await response.json()) as T;
  } else {
    // These HttpError exceptions will now properly propagate to calling code
    console.log(response); // log response to read backend error messages
    switch (status) {
      case 400:
        throw new HttpError(400, "Bad request", response);
      case 401:
        throw new HttpError(401, "Unauthorized", response);
      case 403:
        throw new HttpError(403, "Forbidden", response);
      case 404:
        throw new HttpError(404, "Not found", response);
      case 409:
        throw new HttpError(409, "Conflict", response);
      case 500:
        throw new HttpError(500, "Internal server error", response);
      default:
        throw new HttpError(status, "An unexpected error occurred", response);
    }
  }
}
