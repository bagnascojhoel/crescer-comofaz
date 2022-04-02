import './styles.css';
import React from 'react';
import { BaseButton } from '../../../components/base-button/base-button.component';

export function EditarInteressesActions({ onSkip, onContinue, edit }) {
  return (
    <div className="editar-interesses-actions">
      {edit ? (
        <BaseButton
          className={`
            editar-interesses-actions__button
          `}
          onClick={onContinue}
        >
          editar
        </BaseButton>
      ) : (
        <>
          <BaseButton
            className={`
              editar-interesses-actions__button
              editar-interesses-actions__button--light
            `}
            onClick={onSkip}
          >
            pular
          </BaseButton>
          <BaseButton
            className="editar-interesses-actions__button"
            onClick={onContinue}
          >
            continuar
          </BaseButton>
        </>
      )}
    </div>
  );
}
