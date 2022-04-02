import createGlobalState from 'react-create-global-state';

const [useAuthRequiredMessage, AuthRequiredMessageProvider] = createGlobalState(false);

export { useAuthRequiredMessage, AuthRequiredMessageProvider };
