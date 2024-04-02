import { useCallback } from "react";
import {createSearchParams, useNavigate, useParams, useSearchParams} from "react-router-dom";


const ReadPage:React.FC<Record<string, never>> = () => {

    const {tno} = useParams<{ tno: string }>();

    const navigate = useNavigate()

    //* queryString으로 부터 값을 가져옴.
    const [queryParams] = useSearchParams();


    const page: string = queryParams.get('page')||'1'

    const size: string = queryParams.get('size')||'10'
   
    
    const queryStr: string = createSearchParams({page,size}).toString()//? page=1&size=10
    
    

    //* useCallback을 이용하여서 함수를 메모리에 저장 -> 의존배열의 값이 바뀌지 않는 이상 함수를 다시 불러오지 x
    const moveToModify = useCallback((tno: string|undefined) => {
        
        navigate({
        pathname: `/todo/modify/${tno}`,
        search: queryStr
        })
    
    },[tno, page, size])

    
   
    return ( 
    <div className="text-3xl font-extrabold">
     Todo Read Page Component {tno}
   
     <div>
     <button onClick={() => moveToModify(tno)}>Test Modify</button>
     </div>
     
    </div> 
    );
   
}

export default ReadPage