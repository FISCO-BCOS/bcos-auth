// SPDX-License-Identifier: Apache-2.0
pragma solidity >=0.6.10 <0.8.20;

import "./Committee.sol";
import "./BasicAuth.sol";

contract VoteComputer is BasicAuth {
    // Governors and threshold
    Committee public _committee;

    constructor(address committeeMgrAddress, address committeeAddress) public {
        setOwner(committeeMgrAddress);
        _committee = Committee(committeeAddress);
    }

    /*
     * predicate vote result and return the status
     * @param for voters list
     * @param against voters list
     */
    function determineVoteResult(
        address[] memory agreeVoters,
        address[] memory againstVoters
    ) public view returns (uint8) {
        uint32 agreeVotes = _committee.getWeights(agreeVoters);
        uint32 doneVotes = agreeVotes + _committee.getWeights(againstVoters);
        uint32 allVotes = _committee.getWeights();
        //1. Checks enough voters: totalVotes/totalVotesPower >= p_rate/100
        if (doneVotes * 100 < allVotes * _committee._participatesRate()) {
            //not enough voters, need more votes
            return 1;
        }
        //2. Checks whethere for votes wins: agreeVotes/totalVotes >= win_rate/100
        if (agreeVotes * 100 >= _committee._winRate() * doneVotes) {
            return 2;
        } else {
            return 3;
        }
    }
}
