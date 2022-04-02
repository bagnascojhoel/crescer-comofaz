import { useAuthRequiredMessage } from '../../context';

export function useAuthRequiredMessageManager() {
  const [visible, setVisible] = useAuthRequiredMessage();

  const handleShow = () => {
    setVisible(true);
  };

  const handleHide = () => {
    setVisible(false);
  };

  return { handleHide, handleShow, visible };
}
