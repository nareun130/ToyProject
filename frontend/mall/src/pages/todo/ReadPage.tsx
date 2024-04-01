import { useCallback } from "react";
import {createSearchParams, useNavigate, useParams, useSearchParams} from "react-router-dom";


const ReadPage:React.FC<Record<string, never>> = () => {

    const {tno} = useParams<{ tno: string }>();

    const navigate = useNavigate()

    const [queryParams] = useSearchParams();


    const page: string = queryParams.get('page')||'1'

    const size: string = queryParams.get('size')||'10'
   
    const queryStr: string = createSearchParams({page,size}).toString()
   
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