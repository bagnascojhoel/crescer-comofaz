import './styles.css';
import React from 'react';
import { useHistory } from 'react-router-dom';
import { PAGE_HEADER_BUTTON_SIZE } from '../page-header/page-header.component';
import { IconButton } from '../icon-button/icon-button.component';
import arrowBackIcon from '../../../assets/arrow-back.svg';
import { PageHeaderLogo } from '../page-header-logo/page-header-logo.component';

export function PageHeaderGoBack() {
  const { goBack } = useHistory();
  const handleGoBack = () => goBack();

  return (
    <>
      <header className="page-header-go-back">
        <IconButton
          className="page-header-go-back__button"
          onClick={handleGoBack}
          iconUrl={arrowBackIcon}
          size={PAGE_HEADER_BUTTON_SIZE}
        />
        <PageHeaderLogo />
      </header>
    </>
  );
}
