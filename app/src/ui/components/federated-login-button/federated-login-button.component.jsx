import './styles.css';
import React from 'react';
import { BaseButton } from '../base-button/base-button.component';

export function FederatedLoginButton({ onClick, imageUrl, textColor, text }) {
  return (
    <BaseButton
      onClick={onClick ?? null}
      style={{ color: textColor }}
      className="federated-login-button"
    >
      <FederationLogo url={imageUrl} diameter={15} />
      <p className="federated-login-button__text">{text}</p>
    </BaseButton>
  );
}

function FederationLogo({ url, diameter }) {
  return (
    <div
      style={{
        width: diameter,
        height: diameter,
        backgroundImage: `url(${url})`,
        backgroundPosition: 'center',
        backgroundRepeat: 'no-repeat',
      }}
      className="federated-login-button__federation-logo"
    ></div>
  );
}
