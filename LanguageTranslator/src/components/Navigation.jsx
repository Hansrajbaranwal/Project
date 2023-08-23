import * as React from 'react';
import AppBar from '@mui/material/AppBar';

import Toolbar from '@mui/material/Toolbar';
import { Box, Button, Typography } from '@mui/material';
import { Link } from 'react-router-dom';




function Navigation() {


    return (
        <AppBar position="static">
           <Toolbar>
            <Typography component='h1' varient='h1' >Language Translator</Typography>

            <Box sx={{ display: 'flex'  }}>
              <Button
               component={Link} to='/'
               
                sx={{ my: 2, color: 'white', display: 'block' }}
              >
               Home
              </Button>
              <Button
              component={Link} to='/translate'
               
               sx={{ my: 2, color: 'white', display: 'block' }}
             >
              Translate
             </Button>
             <Button
               component={Link} to='/history'
               sx={{ my: 2, color: 'white', display: 'block' }}
             >
              History
             </Button>
            
          </Box>

          
           </Toolbar>
        </AppBar>
    );
}
export default Navigation;
