import './styles.css';
import React from 'react';
import { ListGalleryItem } from './list-gallery-item/list-gallery-item.component';

export function ListGallery({ data, onClickItem, emptyMessage }) {
  if (data.length === 0)
    return (
      <div className="list-gallery--empty">
        {!emptyMessage ? 'Nenhuma lista' : emptyMessage}
      </div>
    );

  return (
    <ul className="list-gallery">
      {data.map((lista) => (
        <ListGalleryItem onClick={onClickItem} {...lista} key={lista.idLista} />
      ))}
    </ul>
  );
}
