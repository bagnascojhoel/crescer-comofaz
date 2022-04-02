import './styles.css';
import React from 'react';
import { LayoutLabel } from '../layout-label/layout-label.component';

export function InputTextArea({
  register,
  name,
  value,
  onChange,
  errorMessage,
  hasError,
  label,
  placeholder,
  type,
  min,
  noResize,
  noMargin,
  autoFocus,
  defaultValue,
  className,
  style,
}) {
  return (
    <div
      style={style}
      className={`
      input-text-area
      ${className}
      ${noResize && 'input-text-area--no-resize'}
      ${noMargin && 'input-text-area--no-margin'}
     `}
    >
      {label && <LayoutLabel htmlFor={name}>{label}</LayoutLabel>}

      <textarea
        ref={register}
        onChange={onChange}
        className={`input-text-area__input ${
          hasError && 'input-text-area__input--error'
        }`}
        type={type || 'text-area'}
        placeholder={placeholder}
        name={name}
        id={name}
        value={value}
        min={min}
        autoFocus={autoFocus}
        defaultValue={defaultValue}
      />
      {hasError && <span className="input__error-message">{errorMessage}</span>}
    </div>
  );
}
