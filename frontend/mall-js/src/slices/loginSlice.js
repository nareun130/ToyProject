import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { loginPost } from "../api/memberApi";
import { getCookie, removeCookie, setCookie } from "../util/cookieUtil";

const initState = {
  email: "", //! email속성이 있으면 로그인, 없으면 로그인x
};

const loadMemberCookie = () => {
  const memberInfo = getCookie("member");
  if (memberInfo && memberInfo.nickname) {
    //* decodeURIComponent : 웹에서 데이터 전송 시 특수문자 포함 가능성
    memberInfo.nickname = decodeURIComponent(memberInfo.nickname);
  }
  return memberInfo;
};

export const loginPostAsync = createAsyncThunk("loginPostAsync", (param) => {
  return loginPost(param);
});

//*createSlice : Action과 Reducer를 같이 작성
//& -> store.js에 설정 필요
const loginSlice = createSlice({
  //슬라이스 이름. 액션타입 생성 시 사용
  name: "LoginSlice",
  //쿠키가 없으면 초깃값 이용
  initialState: loadMemberCookie() || initState,
  //상태변화시키는 리듀서 함수들 정의
  reducers: {
    //*login(), logout() : 리듀서 함수
    login: (state, action) => {
      console.log("login...");

      //소셜로그인 회원 사용
      const payload = action.payload;

      setCookie("member", JSON.stringify(payload), 1); //1일 동안 Cookie저장
      //새로운 상태
      return payload;
    },
    logout: (state, action) => {
      console.log("logout...");
      removeCookie("member");
      return { ...initState };
    },
  },
  //비동기 호출의 상태에 따라 상태를 변경하는 로직
  extraReducers: (builder) => {
    builder
      .addCase(loginPostAsync.fulfilled, (state, action) => {
        console.log("fulfilled"); //완료
        const payload = action.payload;
        //정상적인 로그인 시에만 저장
        if (!payload.error) {
          //1일만 쿠키 저장
          //*로그인 성공 시 JSON으로 들어옴 -> Cookie에는 문자열만 들어갈 수 있으므로 stringify로 처리
          setCookie("member", JSON.stringify(payload), 1);
        }
        return payload;
      })
      .addCase(loginPostAsync.pending, (state, action) => {
        console.log("pending"); //처리중
      })
      .addCase(loginPostAsync.rejected, (state, action) => {
        console.log("rejected"); //에러
      });
  },
});

export const { login, logout } = loginSlice.actions;

export default loginSlice.reducer;
