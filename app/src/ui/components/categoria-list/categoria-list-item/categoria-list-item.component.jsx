import React from 'react';
import { BaseButton } from '../../base-button/base-button.component';

export function CategoriaListItem({ onClick, nome, idCategoria, selected }) {
  const handleClick = () => {
    if (onClick) {
      onClick(idCategoria);
    }
  };

  return (
    <li
      className={`
        category-list-item
        ${selected && 'category-list-item--selected'}
        ${!onClick && 'category-list-item--for-layout'}
      `}
    >
      {!onClick ? (
        <span
          className={`
            category-list-item__background
            category-list-item__background--for-layout
          `}
        >
          {nome}
        </span>
      ) : (
        <BaseButton
          type="button"
          className="category-list-item__background category-list-item__button"
          onClick={handleClick}
        >
          {nome}
        </BaseButton>
      )}
    </li>
  );
}
