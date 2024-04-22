import { getList } from "../../api/productsApi";
import useCustomMove from "../../hooks/useCustomMove";
import FetchingModal from "../common/FetchingModal";

import { useQuery, useQueryClient } from "@tanstack/react-query";
import { API_SERVER_HOST } from "../../api/todoApi";
import useCustomLogin from "../../hooks/useCustomLogin";
import PageComponent from "../common/PageComponent";

const host = API_SERVER_HOST;

const initState = {
  dtoList: [],
  pageNumList: [],
  pageRequestDTO: null,
  prev: false,
  next: false,
  totoalCount: 0,
  prevPage: 0,
  nextPage: 0,
  totalPage: 0,
  current: 0,
};

const ListComponent = () => {
  const { moveToLoginReturn } = useCustomLogin();

  const { page, size, refresh, moveToList, moveToRead } = useCustomMove();

  //* staleTime의 위치 주의!!
  const { isFetching, data, error, isError } = useQuery(
    ["products/list", { page, size, refresh }],
    () => getList({ page, size }),
    { staleTime: 1000 * 5 }
  );
  // const queryClient = useQueryClient(); // 리액트 쿼리 초기화를 위한 객체

  //* 동일 페이지를 다시 클릭하면 리액트 쿼리의 키 값의 데이터를 무효화 시켜서 다시 데이터 조회
  const handleClickPage = (pageParam) => {
    //& -> useQuery에 refresh와 staleTime을 설정함으로써 필요가 없어짐.
    // if (pageParam.page === parseInt(page)) {
    //   queryClient.invalidateQueries("products/list");
    // }
    moveToList(pageParam);
  };

  if (isError) {
    console.log(error);
    return moveToLoginReturn();
  }
  const serverData = data || initState;

  return (
    <div className="border-2 border-blue-100 mt-10 mr-2 ml-2">
      {isFetching ? <FetchingModal /> : <></>}

      <div className="flex flex-wrap mx-auto p-6">
        {serverData.dtoList.map((product) => (
          <div
            key={product.pno}
            className="w-1/2 p-1 rounded shadow-md border-2"
            onClick={() => moveToRead(product.pno)}>
            <div className="flex flex-col  h-full">
              <div className="font-extrabold text-2xl p-2 w-full ">
                {product.pno}
              </div>
              <div className="text-1xl m-1 p-2 w-full flex flex-col">
                <div className="w-full overflow-hidden ">
                  <img
                    alt="product"
                    className="m-auto rounded-md w-60"
                    src={`${host}/api/products/view/s_${product.uploadFileNames[0]}`}
                  />
                </div>

                <div className="bottom-0 font-extrabold bg-white">
                  <div className="text-center p-1">이름: {product.pname}</div>
                  <div className="text-center p-1">가격: {product.price}</div>
                </div>
              </div>
            </div>
          </div>
        ))}
      </div>

      <PageComponent
        serverData={serverData}
        movePage={handleClickPage}></PageComponent>
    </div>
  );
};

export default ListComponent;
