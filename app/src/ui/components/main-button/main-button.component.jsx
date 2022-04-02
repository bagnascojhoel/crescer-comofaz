import './styles.css';
import React from 'react';
import { BaseButton } from '../base-button/base-button.component';

export function MainButton({ onClick, to, className, disabled, small, red, outlined, submit, children }) {
  return (
    <BaseButton
      onClick={onClick}
      type={submit ? 'submit' : 'button'}
      to={to}
      className={`
        main-button
        ${disabled && 'main-button--disabled'}
        ${small && 'main-button--small'}
        ${red && 'main-button--red'}
        ${outlined && 'main-button--outlined'}
        ${className}
      `}
      disabled={disabled}

    >
      {children}
    </BaseButton>
  );
}
