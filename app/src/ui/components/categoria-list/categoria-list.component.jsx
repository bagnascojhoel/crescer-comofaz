import './styles.css';
import React from 'react';
import { CategoriaListItem } from './categoria-list-item/categoria-list-item.component';

export function CategoriaList({
  name,
  onChange,
  value,
  categorias,

  className,
  style,
  forLayout,
  row,
}) {
  return (
    <div className={`category-list ${className}`}>
      <ul
        style={style}
        id={name}
        className={`category-list__list ${row && 'category-list__list--row'}`}
      >
        {categorias.map(({ idCategoria, nome }) => (
          <CategoriaListItem
            key={idCategoria}
            selected={value && value.includes(idCategoria)}
            onClick={!forLayout && onChange}
            nome={nome}
            idCategoria={idCategoria}
          />
        ))}
      </ul>
    </div>
  );
}
