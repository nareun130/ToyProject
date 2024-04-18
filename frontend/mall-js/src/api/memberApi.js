import axios from "axios";
import { API_SERVER_HOST } from "./todoApi";

const host = `${API_SERVER_HOST}/api/member`;

export const loginPost = async (loginParam) => {
  const header = {
    headers: { "Content-Type": "application/x-www-form-urlencoded" },
  };

    const form = new FormData();
    form.append("username", loginParam.email);
    form.append("password", loginParam.pw);
    // const params = new URLSearchParams();
    // params.append('username',loginParam.email);
    // params.append('password',loginParam.pw);
    // const body = `username=${encodeURIComponent(loginParam.email)}&password=${encodeURIComponent(loginParam.pw)}`;

  const res = await axios.post(`${host}/login`,form, header);

  return res.data;
};
