import './styles.css';
import React from 'react';
import { BaseButton } from '../base-button/base-button.component';

export function LightButton({
  to,
  onClick,
  iconUrl,
  secondary,
  mini,
  submit,
  hidden,
  className,
  children,
}) {
  return (
    <BaseButton
      type={submit ? 'submit' : 'button'}
      className={`
        light-button
        ${hidden && 'light-button--hidden'}
        ${className}
      `}
      to={to}
      onClick={onClick}
    >
      <img className="light-button__icon" src={iconUrl} alt="" />
      <span
        className={`
          light-button__text
          ${secondary && 'light-button__text--secondary'}
          ${mini && 'light-button__text--mini'}
        `}
      >
        {children}
      </span>
    </BaseButton>
  );
}
