import './styles.css';
import React from 'react';
import { Rating, LayoutLabel } from '../';

export function InputRating({
  label,
  value,
  onChange,
  hasError,
  errorMessage,
  largeIcon,
  className,
}) {
  return (
    <div className={`input-rating ${className}`}>
      <div className="input-rating__content-row">
        {label && <LayoutLabel className="input__label">{label}</LayoutLabel>}
        <Rating onClick={onChange} value={value} largeIcon={largeIcon} />
      </div>
      {hasError && <span className="input__error-message">{errorMessage}</span>}
    </div>
  );
}
