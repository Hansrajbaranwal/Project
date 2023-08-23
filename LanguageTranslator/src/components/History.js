import React, { useState, useEffect } from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';


const History = () => {

  const [history, setHistory] = useState();
  const translationHistory = [
    { id: 1, originalText: 'Hello', translatedText: 'Hola' },
    { id: 2, originalText: 'Goodbye', translatedText: 'Adiós' },


  ];

  useEffect(() => {


    setHistory(translationHistory);

  }, []);

  const handleDeleteEntry = (entryId) => {

  };

  return (
    <div style={{margin:"70px"}}>
      <h2>Translation History</h2>

      <TableContainer component={Paper}>
      <Table sx={{ minWidth: 650 }} aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell>ID </TableCell>
            <TableCell >Original Text</TableCell>
            <TableCell >Translated Text</TableCell>
           
          </TableRow>
        </TableHead>
        <TableBody>
          {history?.map((item) => (
            <TableRow
              key={item.id}
              sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
            >
              <TableCell component="th" scope="row">
                {item.id}
              </TableCell>
              <TableCell >{item.originalText}</TableCell>
              <TableCell >{item.translatedText}</TableCell>
             
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>

      {/* {
        history?.map((item,key)=>(
          <div key={item.id}>
            <h2>{item.originalText}</h2>

          </div>
        ))
      } */}



    </div>
  );
}

export default History;
