import './styles.css';
import React from 'react';
import { EditarInteressesListItem } from '../editar-interesses-list-item/editar-interesses-list-item.component';

export function EditarInteressesList({
  onChange,
  value,
  data,
  keyExtractor,
}) {

  const _onChange = (id) => {
    if (value && value.includes(id))
      onChange(value.filter((it) => it !== id));
    else if (value) onChange([...value, id]);
    else onChange([id]);
  };

  return (
    <ul className="editar-interesses-list">
      {data.map((categoria) => (
        <EditarInteressesListItem
          onClick={_onChange}
          selected={value && value.includes(categoria.idCategoria)}
          key={keyExtractor(categoria)}
          {...categoria}
        />
      ))}
    </ul>
  );
}
