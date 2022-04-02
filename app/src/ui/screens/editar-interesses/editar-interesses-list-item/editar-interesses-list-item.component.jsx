import './styles.css';
import React from 'react';
import { BaseButton } from '../../../components/base-button/base-button.component';

export function EditarInteressesListItem({
  onClick,
  selected,
  idCategoria,
  foto,
  nome,
}) {
  const handleClick = () => {
    onClick(idCategoria);
  };

  return (
    <BaseButton
      onClick={handleClick}
      className={`
        editar-interesses-list-item
        ${selected && 'editar-interesses-list-item--selected'}
      `}
    >
      <div
        className="editar-interesses-list-item__background"
        style={{ backgroundImage: `url(${foto})` }}
      ></div>
      <span className="editar-interesses-list-item__text">{nome}</span>
    </BaseButton>
  );
}
