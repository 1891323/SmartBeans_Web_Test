/*
 * Copyright 2011 MOPAS(Ministry of Public Administration and Security).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package smartbeans.portal.sym.mpm.mapper;

import smartbeans.cmmn.ComDefaultVO;
import smartbeans.portal.sym.mpm.vo.PrgrmVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

@Mapper("prgrmMapper")
public interface PrgrmMapper {

	void insertPrgrm(PrgrmVO vo) throws Exception;

	void updatePrgrm(PrgrmVO vo) throws Exception;

	void deletePrgrm(PrgrmVO vo) throws Exception;

	PrgrmVO selectPrgrm(PrgrmVO vo) throws Exception;

	List<?> selectPrgrmList(ComDefaultVO searchVO) throws Exception;

	int selectPrgrmListTotCnt(ComDefaultVO searchVO);

	List<?> selectPrgrmListForAuth(ComDefaultVO searchVO);
}
