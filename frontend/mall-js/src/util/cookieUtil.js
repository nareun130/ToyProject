import { Cookies } from "react-cookie";

const cookies = new Cookies();

export const setCookie = (name, value, days) => {
  const expires = new Date();
  //보관 기한
  expires.setUTCDate(expires.getUTCDate() + days);

  //*path:/ ->쿠키가 웹사이트의 모든 경로에서 접근 가능하도록
  return cookies.set(name, value, { path: "/", expires: expires });
};

export const getCookie = (name) => {
  return cookies.get(name);
};

export const removeCookie = (name, path = "/") => {
  cookies.remove(name, { path });
};
