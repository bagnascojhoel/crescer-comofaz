import './styles.css';
import React from 'react';
import { BaseButton } from '../base-button/base-button.component';

export function TabsHeader({ onTabClick, tabs, currentTab }) {
  return (
    <div className="tabs-header">
      {tabs.map((name, i) => {
        const _onTabClick = () => onTabClick(i);

        return (
          <BaseButton
            onClick={_onTabClick}
            className={`
              tabs-header__button
              ${currentTab === i && 'tabs-header__button--active'}
            `}
          >
            {name}
          </BaseButton>
        );
      })}
    </div>
  );
}
