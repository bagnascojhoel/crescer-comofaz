import './styles.css';
import React from 'react';
import { useApiErrorMessageManager } from '../../../hooks';
import { Modal, MainButton, LayoutRow } from '../';

export function ApiErrorMessage() {
  const { dismiss, message } = useApiErrorMessageManager();
  return (
    <Modal show={!!message} onClose={dismiss} className="api-error-message">
      <div className="api-error-message__content">
        <LayoutRow alignStart>
          <ErrorIcon />
          <p className="api-error-message__text">{message}</p>
        </LayoutRow>

        <LayoutRow alignEnd>
          <MainButton onClick={dismiss} small red>
            okay...
          </MainButton>
        </LayoutRow>
      </div>
    </Modal>
  );
}

function ErrorIcon() {
  return (
    <svg
      className="api-error-message__icon"
      xmlns="http://www.w3.org/2000/svg"
      viewBox="0 0 24 24"
    >
      <path d="M0 0h24v24H0V0z" fill="none" />
      <path
        d="M11 15h2v2h-2zm0-8h2v6h-2zm.99-5C6.47 2 2 6.48 2 12s4.47 10 9.99 10C17.52 22 22 17.52 22 12S17.52 2 11.99 2zM12 20c-4.42 0-8-3.58-8-8s3.58-8 8-8 8 3.58 8 8-3.58 8-8 8z"
      />
    </svg>
  );
}
