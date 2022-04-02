import './styles.css';
import React from 'react';
import { BaseButton } from '../../base-button/base-button.component';

export function ListGalleryItem({ onClick, idLista, assunto, privado }) {
  const _onClick = () => onClick(idLista);

  return (
    <li className="list-gallery-item">
      <BaseButton onClick={_onClick} className="list-gallery-item__button">
        {assunto}
      </BaseButton>
    </li>
  );
}
