import { useApiErrorMessage } from '../../context/api-error-message/api-error-message.context';

export function useApiErrorMessageManager() {
  const [message, setMessage] = useApiErrorMessage();

  const send = (message) => {
    setMessage(message);
  };

  const dismiss = () => {
    setMessage(null);
  };

  return { send, dismiss, message };
}
