import useCustomLogin from "../hooks/useCustomLogin";
import BasicLayout from "../layouts/BasicLayout";

const AboutPage = () => {
  const {isLogin, moveToLoginReturn} = useCustomLogin();

  //로그인 한 사용자만 AboutPage로 이동시켜주기 위해
  if(!isLogin){
    return moveToLoginReturn();
  }
  return (
    <BasicLayout>
      <div className=" text-3xl">About Page</div>
    </BasicLayout>
  );
};

export default AboutPage;
