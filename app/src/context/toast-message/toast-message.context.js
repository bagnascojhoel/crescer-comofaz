import createGlobalState from 'react-create-global-state';

const [useToastMessage, ToastMessageProvider] = createGlobalState(null);

export { useToastMessage, ToastMessageProvider };
