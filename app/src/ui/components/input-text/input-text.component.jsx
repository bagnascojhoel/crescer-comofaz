import './styles.css';
import React from 'react';
import { LayoutLabel } from '../layout-label/layout-label.component';

export function InputText({
  onChange,
  register,
  label,
  placeholder,
  name,
  value,
  type,
  id,

  errorMessage,
  hasError,

  autoFocus,
  noMargin,
  min,
  className,
  defaultValue,
}) {
  const _onChange = (e) => onChange && onChange(e.target.value);

  return (
    <div
      className={`
        input-text
        ${noMargin && 'input-text--no-margin'}
        ${className}
      `}
    >
      {label && <LayoutLabel htmlFor={name}>{label}</LayoutLabel>}

      <input
        className={`input-text__input ${
          hasError && 'input-text__input--error'
        }`}
        ref={register}
        onChange={_onChange}
        type={type || 'text'}
        placeholder={placeholder}
        name={name}
        id={id || name}
        value={value}
        min={min}
        autoFocus={autoFocus}
        defaultValue={defaultValue}
      />
      {hasError && <span className="input__error-message">{errorMessage}</span>}
    </div>
  );
}
