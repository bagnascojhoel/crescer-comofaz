import './styles.css';
import React from 'react';
import { BaseButton } from '../base-button/base-button.component';

export function IconButton({
  onClick,
  size,
  iconUrl,
  className,
}) {

  return (
    <BaseButton
      type="button"
      onClick={onClick}
      className={`icon-button ${className}`}
    >
      {iconUrl && (
        <img
          style={{
            width: size,
          }}
          className="icon-button__icon"
          src={iconUrl}
          alt=""
        />
      )}
    </BaseButton>
  );
}

IconButton.defaultProps = {
  size: 20,
};
