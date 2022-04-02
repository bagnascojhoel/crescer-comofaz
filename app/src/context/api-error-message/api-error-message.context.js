import createGlobalState from 'react-create-global-state';

const [useApiErrorMessage, ApiErrorMessageProvider] = createGlobalState(null);

export { useApiErrorMessage, ApiErrorMessageProvider };
