import './styles.css';
import { Select } from 'antd';
import React from 'react';
import { LayoutLabel } from '../layout-label/layout-label.component';

export function InputSelect({
  name,
  onChange,
  value,
  options,

  hasError,
  errorMessage,

  label,
  placeholder,
  noMargin,
  className,
}) {
  return (
    <div
      className={`
        input-select
        ${noMargin && 'input-select--no-margin'}
        ${className}
      `}
    >
      <div className="input-select__content-row">
        {label && (
          <LayoutLabel htmlFor={name} className="input__label">
            {label}
          </LayoutLabel>
        )}

        <Select
          id={name}
          className={`
          input-select__input
          ${hasError && 'input-select__input--error'}
        `}
          onChange={onChange}
          value={value}
          placeholder={placeholder}
        >
          {options.map(({ label, value }) => (
            <Select.Option value={value} key={value}>
              {label}
            </Select.Option>
          ))}
        </Select>
      </div>
      {hasError && <span className="input__error-message">{errorMessage}</span>}
    </div>
  );
}
