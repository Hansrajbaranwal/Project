import React from 'react';
import Alert from '@mui/material/Alert';

function TranslationResult({ translatedText, error }) {
  return (
    <div>
      {error ? (
        <Alert severity="error">Error: {error}</Alert>
      ) : (
        <div>
          {translatedText && <Alert severity="success">{translatedText}</Alert>}
        </div>
      )}
    </div>
  );
}

export default TranslationResult;
