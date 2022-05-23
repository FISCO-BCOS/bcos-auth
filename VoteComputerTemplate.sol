// SPDX-License-Identifier: Apache-2.0
pragma solidity >=0.6.10 <0.8.20;

import "./Committee.sol";
import "./BasicAuth.sol";

abstract contract VoteComputerTemplate is BasicAuth {
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
    ) public view virtual returns (uint8);

    /*
     * calculate vote result and return the status,
     * this method only for committee check calculate logic
     * @param agree voter totoal weight
     * @param voted voter totoal weight
     * @param all voter totoal weight
     * @param paricipate threshold, percentage
     * @param win threshold, percentage
     */
    function voteResultCalc(
        uint32 agreeVotes,
        uint32 doneVotes,
        uint32 allVotes,
        uint8 participatesRate,
        uint8 winRate
    ) public pure virtual returns (uint8);
}
