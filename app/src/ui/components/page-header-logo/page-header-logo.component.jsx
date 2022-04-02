import React from 'react';
import { ROUTES } from '../../../routes';
import { BaseButton } from '../base-button/base-button.component';

export function PageHeaderLogo() {
  return (
    <BaseButton to={ROUTES.HOME}>
      <img
        className="page-header__logo"
        src="/logo-horizontal.svg"
        alt="Logo ComoFaz"
      />
    </BaseButton>
  );
}
