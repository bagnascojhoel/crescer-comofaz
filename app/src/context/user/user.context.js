import createGlobalState from 'react-create-global-state';

const [useGlobalUser, UserProvider] = createGlobalState(null);

export { useGlobalUser, UserProvider };
