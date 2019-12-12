import stardog

conn_details = {
    'endpoint': 'http://45.55.41.230:5820',
    'username': 'admin',
    'password': 'admin'
}

PARENT_OF = ":parentOf"
REQUIRES = ":requires"

def getTerms(input_term):
    with stardog.Admin(**conn_details) as admin:
        with stardog.Connection('capability', **conn_details) as conn:
            # get all equivalent calsses query
            # No. 1 : get all parents
            query_for_parents = """
                SELECT ?entity where {{
                    ?src {0}* <http://stardog.com/{1}> .
                    FILTER(?src != <http://stardog.com/ManufacturingCapabilities>)
                    BIND(?src AS ?entity) .                   
                }}
                """.format(PARENT_OF, input_term)
            result_for_parents = conn.select(query_for_parents, reasoning=True)

            # No. 2 : get all requires
            query_for_requires = """
                SELECT ?entity where {{
                    <http://stardog.com/{1}> {0}* ?tgt .
                    FILTER(?tgt != <http://stardog.com/ManufacturingCapabilities>)
                    BIND(?tgt AS ?entity) .                   
                }}
                """.format(REQUIRES, input_term)
            result_for_requires = conn.select(query_for_requires, reasoning=True)

            stardog_responses = [result_for_parents, result_for_requires]

            result_list = set()
            for response in stardog_responses:
                for result in response['results']['bindings']:
                    temp_out = result[next(iter(result))]['value']
                    temp_out = temp_out.rsplit("/", 1)[-1]
                    result_list.add(temp_out)

            return result_list