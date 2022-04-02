import './styles.css';
import React, { useEffect } from 'react';
import { useFieldArray, useWatch } from 'react-hook-form';
import {
  IconButton,
  MainButton,
  InputTextArea,
  LayoutLabel,
  InputImage,
} from '..';
import closeIcon from '../../../assets/close.svg';

export function InputPassoList({ register, control, name, defaultValue }) {
  const { append, remove, fields } = useFieldArray({ name, control });

  const passoList = useWatch({ name, control });

  useEffect(() => {
    if (defaultValue) {
      defaultValue.forEach((v) =>
        append({ texto: v.texto, foto: v.foto }, false)
      );
    } else {
      append({ texto: '', foto: null }, false);
    }
  }, []);

  return (
    <div className="input-text-list">
      <LayoutLabel className="input-text-list__label input__label">
        Passos
      </LayoutLabel>

      <ul className="input-text-list__list">
        {fields.map((item, i) => (
          <li key={item.id} className="input-text-list-item">
            <InputImage
              control={control}
              name={`${name}[${i}].foto`}
              defaultValue={item.foto}
            />

            <InputTextArea
              placeholder="Descrição do passo"
              className="input-text-list-item__input-text"
              name={`${name}[${i}].texto`}
              register={register}
              defaultValue={item.texto}
              noResize
            />

            {passoList[i].texto && (
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
        onClick={() => append({})}
        small
        red
      >
        {fields.length === 0 ? 'adicionar passo' : 'adicionar outro passo'}
      </MainButton>
    </div>
  );
}
