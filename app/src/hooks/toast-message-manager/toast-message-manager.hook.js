import { useToastMessage } from '../../context';

export function useToastMessageManager() {
  const [message, setMessage] = useToastMessage();

  const send = (message) => {
    setMessage(null);
    setMessage(message);
  };

  const dismiss = () => {
    setMessage(null);
  };

  return { dismiss, send, message };
}
