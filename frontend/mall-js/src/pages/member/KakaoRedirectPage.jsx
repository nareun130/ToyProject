import { useEffect } from "react";
import { useSearchParams } from "react-router-dom";
import { getAccessToken, getMemberWithAccessToken } from "../../api/kakaoApi";
import useCustomLogin from "../../hooks/useCustomLogin";

const KakaoRedirectPage = () => {
  const [searchParams] = useSearchParams();

  const { moveToPath, saveAsCookie } = useCustomLogin();

  const authCode = searchParams.get("code");

  useEffect(() => {
    getAccessToken(authCode).then((accessToken) => {
      console.log(accessToken);

      getMemberWithAccessToken(accessToken).then((memberInfo) => {
        console.log("--------------");
        console.log(memberInfo);

        saveAsCookie(memberInfo);
        //소설 회원이 아닌 경우
        if (memberInfo && !memberInfo.social) {
          moveToPath("/");
        } else {
          //db에 없는 사용자가 카카오 로그인을 한 경우 -> 수정페이지
          moveToPath("/member/modify");
        }
      });
    });
  }, [authCode]);

  return (
    <div>
      <div>Kakako Login Redirect</div>
      <div>{authCode}</div>
    </div>
  );
};

export default KakaoRedirectPage;
