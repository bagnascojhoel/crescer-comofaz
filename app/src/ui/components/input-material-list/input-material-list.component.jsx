import './styles.css';
import React, { useEffect } from 'react';
import { useFieldArray, useWatch } from 'react-hook-form';
import { IconButton, MainButton, InputText, LayoutLabel } from '..';
import closeIcon from '../../../assets/close.svg';

export function InputMaterialList({ control, register, name, defaultValue }) {
  const { fields, append, remove } = useFieldArray({
    control,
    name,
  });

  const materialList = useWatch({
    control,
    name,
  });

  useEffect(() => {
    if (defaultValue) defaultValue.forEach((value) => append({ value }, false));
    else append({}, false);
  }, []);

  return (
    <div className="input-text-list">
      <LayoutLabel>Materiais</LayoutLabel>

      <ul className="input-text-list__list">
        {fields.map((item, i) => (
          <li key={item.id} className="input-text-list-item">
            <InputText
              className="input-text-list-item__input"
              name={`${name}[${i}].value`}
              register={register}
              defaultValue={item.value}
              autoFocus={false}
            />

            {materialList[i].value && (
              <IconButton
                className="input-text-list-item__close-button"
                onClick={() => remove(i)}
                iconUrl={closeIcon}
              />
            )}
          </li>
        ))}
      </ul>
      <MainButton
        className="input-text-list__append-button"
        onClick={() => append({ value: null })}
        small
        red
      >
        {fields.length === 0 ? 'adicionar material' : 'adicionar outro material'}
      </MainButton>
    </div>
  );
}
