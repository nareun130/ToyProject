import { RouterProvider } from "react-router-dom";
import root from "./router/root";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { ReactQueryDevtools } from "@tanstack/react-query-devtools";

const queryClient = new QueryClient();//* 리액트 쿼리의 기본 설정

function App() {
  return (
    <QueryClientProvider client={queryClient}>
      <RouterProvider router={root} />
      {/*//* initalIsOpen : 구동 시에 개발 도구 오픈 */}
      <ReactQueryDevtools initialIsOpen={true} />
    </QueryClientProvider>
  );
}

export default App;
