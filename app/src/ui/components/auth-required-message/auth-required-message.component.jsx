import './styles.css';
import React from 'react';
import { Modal } from '../modal/modal.component';
import { useAuthRequiredMessageManager } from '../../../hooks';
import { MainButton } from '../main-button/main-button.component';
import { LayoutRow } from '../layout-row/layout-row.component';
import { ROUTES } from '../../../routes';

export function AuthRequiredMessage() {
  const { handleHide, visible } = useAuthRequiredMessageManager();

  return (
    <Modal onClose={handleHide} show={visible} bottom>
      <p className="auth-required-message__text">
        Você precisa estar logado para utilizar esta funcionalidade.
      </p>

      <LayoutRow>
        <MainButton
          onClick={handleHide}
          className="auth-required-message__button auth-required-message__button--spacing"
          outlined
        >
          fechar
        </MainButton>

        <MainButton
          to={ROUTES.AUTENTICACAO}
          onClick={handleHide}
          className="auth-required-message__button"
        >
          ir para login
        </MainButton>
      </LayoutRow>
    </Modal>
  );
}
