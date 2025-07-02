export default function ErrorMessage({ message }: { message: string }) {
  return (
    <div
      className="bg-red-200 text-red-800 px-4 py-2 rounded-xl text-center"
      role="alert"
    >
      {message}
    </div>
  );
}
