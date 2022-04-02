export default function buildHeaders(user) {
  return user
    ? {
        authorization: user.token,
        'X-Authorization-User-Id': user.userId,
        'X-Authorization-Provider': user.federation,
      }
    : {};
}
