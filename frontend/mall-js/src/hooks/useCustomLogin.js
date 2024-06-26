import { Navigate, createSearchParams, useNavigate } from "react-router-dom";
import { useRecoilState, useResetRecoilState } from "recoil";
import { loginPost } from "../api/memberApi";
import signinState from "../atoms/signinState";
import { removeCookie, setCookie } from "../util/cookieUtil";
import { cartState } from "../atoms/cartState";

const useCustomLogin = () => {
  const navigate = useNavigate();

  const [loginState, setLoginState] = useRecoilState(signinState);

  const resetState = useResetRecoilState(signinState);

  const resetCartState = useResetRecoilState(cartState);

  const isLogin = loginState.email ? true : false; //----------로그인 여부

  const doLogin = async (loginParam) => {
    const result = await loginPost(loginParam);

    console.log(result);

    saveAsCookie(result);

    return result;
  };

  const saveAsCookie = (data) => {
    setCookie("member", JSON.stringify(data), 1);

    setLoginState(data);
  };

  const doLogout = () => {
    removeCookie("member");
    resetState();
    resetCartState();
  };

  const moveToPath = (path) => {
    //----------------페이지 이동
    //*{replace:true} : 뒤로가기로 로그인 화면을 볼 수 없도록
    navigate({ pathname: path }, { replace: true });
  };

  const moveToLogin = () => {
    //----------------------로그인 페이지로 이동
    navigate({ pathname: "/member/login" }, { replace: true });
  };

  const moveToLoginReturn = () => {
    //----------------------로그인 페이지로 이동 컴포넌트
    return <Navigate replace to="/member/login/" />;
  };

  //토큰에 따른 예외 처리
  const exceptionHandle = (ex) => {
    console.log("Exception------------------------");

    console.log(ex);

    const errorMsg = ex.response.data.error;

    const errorStr = createSearchParams({ error: errorMsg }).toString();

    if (errorMsg === "REQUIRE_LOGIN") {
      alert("로그인 해야만 합니다.");
      navigate({ pathname: "/member/login", search: errorStr });

      return;
    }

    if (ex.response.data.error === "ERROR_ACCESSDENIED") {
      alert("해당 메뉴를 사용할 수 있는 권한이 없습니다.");
      navigate({ pathname: "/member/login", search: errorStr });
      return;
    }
  };

  return {
    loginState,
    isLogin,
    doLogin,
    doLogout,
    saveAsCookie,
    moveToPath,
    moveToLogin,
    moveToLoginReturn,
    exceptionHandle,
  };
};

export default useCustomLogin;
