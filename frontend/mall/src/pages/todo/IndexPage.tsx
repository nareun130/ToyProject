import { Outlet, useNavigate} from "react-router-dom";
import BasicLayout from "../../layouts/BasicLayout";

//* Record<string,never> 프로퍼티 값에 never 지정 - 어떤 값도 담기지 않을 빈 객체임을 나타냄.
//* Record<string, unknown> 프로퍼티 값에 unknown지정 - 모든 타입의 값이 할당될 수 있는 객체임을 명시
const IndexPage:React.FC<Record<string, never>> = () => {

    const navigate  = useNavigate()

    const handleClickList = () : void => {
        navigate({pathname:'list'})
    }
    const handleClickAdd = () : void => {
        navigate({pathname:'add'})
    }


    return (
        <BasicLayout>
            <div className="w-full flex m-2 p-2 ">
                <div
                    className="text-xl m-1 p-2 w-20 font-extrabold text-center underline"
                    onClick={handleClickList}
                >LIST</div>
                <div
                    className="text-xl m-1 p-2 w-20 font-extrabold text-center underline"
                    onClick={handleClickAdd}
                >ADD</div>
            </div>
            <div className="flex flex-wrap w-full">
                {/* //* Outlet : 중첩 라우팅 설정 시 레이아웃 유지가 가능! */}
                <Outlet/>
            </div>
        </BasicLayout>
    );
}

export default IndexPage;