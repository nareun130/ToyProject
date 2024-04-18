import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { loginPost } from "../api/memberApi";

const initState = {
  email: "", //! email속성이 있으면 로그인, 없으면 로그인x
};

export const loginPostAsync = createAsyncThunk("loginPostAsync", (param) => {
  return loginPost(param);
});

//*createSlice : Action과 Reducer를 같이 작성
//& -> store.js에 설정 필요
const loginSlice = createSlice({
  //슬라이스 이름. 액션타입 생성 시 사용
  name: "LoginSlice",
  initialState: initState,
  //상태변화시키는 리듀서 함수들 정의
  reducers: {
    //*login(), logout() : 리듀서 함수
    login: (state, action) => {
      console.log("login...");
      //{eamil,pw} 구성
      const data = action.payload;

      //새로운 상태
      return { email: data.email };
    },
    logout: (state, action) => {
      console.log("logout...");
      return { ...initState };
    },
  },
  //비동기 호출의 상태에 따라 상태를 변경하는 로직
  extraReducers: (builder) => {
    builder
      .addCase(loginPostAsync.fulfilled, (state, action) => {
        console.log("fulfilled"); //완료

        const payload = action.payload
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
