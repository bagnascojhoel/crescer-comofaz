import './styles.css';
import React from 'react';
import { Link } from 'react-router-dom';

export function BaseButton({
  onClick,
  to,

  type,
  disabled,

  noHover,
  style,
  className,

  children,
}) {
  if (!to && (onClick || type === 'submit'))
    return (
      <button
        type={type}
        style={style}
        onClick={onClick}
        className={`base-button ${className} ${
          noHover && 'base-button--no-hover'
        }`}
        disabled={disabled}
      >
        {children}
      </button>
    );
  else if (to)
    return (
      <Link
        style={style}
        to={to}
        onClick={onClick}
        className={`
          base-button base-button--link
          ${className}
          ${noHover && 'base-button--no-hover'}
        `}
      >
        {children}
      </Link>
    );
  else return <p>Passe o prop 'to' ou 'onClick'</p>;
}
