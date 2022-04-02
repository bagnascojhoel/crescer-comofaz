import './styles.css';
import React, { useEffect } from 'react';
import { useToastMessageManager } from '../../../hooks';

const DISMISS_TIME = 4000;

export function ToastMessage() {
  const { dismiss, message } = useToastMessageManager();

  useEffect(() => {
    if (message) {
      const interval = setInterval(() => {
        dismiss();
        clearInterval(interval);
      }, DISMISS_TIME);
    }
  }, [message]);

  return (
    <div className={`toast ${!message && 'toast--hidden'}`}>{message}</div>
  );
}
