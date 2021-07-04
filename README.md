# jusbrasil-process-search-api

## Padrão CNJ para Numeração de Processos Jurídicos: Numeração Única de Processos

- 20 dígitos
- formato NNNNNNN-DD.AAAA.J.TR.OOOO
- NNNNNNN, com 7 dígitos, é o número seqüencial do processo por unidade de origem (OOOO), a ser reiniciado a cada ano
- DD, com 2 dígitos, é o dígito verificador (algoritmo Módulo 97 Base 10, conforme Norma ISO 7064:2003, nos termos das instruções constantes do Anexo VIII desta Resolução.)
- AAAA, com 4 dígitos, é o ano do ajuizamento do processo.
- J, com 1 (um) dígito, é o órgão ou segmento do Poder Judiciário, observada a seguinte correspondência: 1 a 9. Nesse casa será sempre 8 (Justiça dos Estados e do Distrito Federal e Territórios).
- TR, com 2 (dois) dígitos, é o tribunal do respectivo segmento do Poder Judiciário e, na Justiça Militar da União, a Circunscrição Judiciária. Números válidos vão de 0 a 27 e 90. Os números aceitos serão dos tribunais em questão: 02 e 12.
- OOOO, com 4 dígitos, é a unidade de origem do processo, observadas as estruturas administrativas dos segmentos do Poder Judiciário e as seguintes diretrizes: de 0 a 9999. 
