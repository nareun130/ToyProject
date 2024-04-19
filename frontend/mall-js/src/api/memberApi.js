import axios from "axios";
import { API_SERVER_HOST } from "./todoApi";

const host = `${API_SERVER_HOST}/api/member`;

export const loginPost = async (loginParam) => {
  const header = {
    headers: { "Content-Type": "application/x-www-form-urlencoded" },
  };

    const form = new FormData();
    //TODO : application/x-www-form-urlencoded 방식으로 전송 안됨.
    form.append("username", loginParam.email);
    form.append("password", loginParam.pw);

  const res = await axios.post(`${host}/login`,form, header);

  return res.data;
};
