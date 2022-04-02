export function useStorageManager() {
  const USER_KEY = 'USER';

  const keep = (object) => {
    const stringifiedObject = JSON.stringify(object);
    localStorage.setItem(USER_KEY, stringifiedObject);
  };

  const retrieve = () => {
    return JSON.parse(localStorage.getItem(USER_KEY));
  };

  const clear = () => {
    localStorage.removeItem(USER_KEY);
    localStorage.clear();
  };

  return {
    keep,
    retrieve,
    clear,
  };
}
